package yass.stephanie.com.incommon.Home

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import yass.stephanie.com.incommon.R


class HomeFragment : Fragment(), OnMapReadyCallback {


    companion object {
        var map: GoogleMap? = null
        private const val ZOOM = 17f
        private const val REQUEST_CODE = 65535
        private const val TIME_INTERVAL: Long = 10000
        private const val FASTEST_INTERVAL: Long = 5000
        private lateinit var fusedLocationClient: FusedLocationProviderClient
        private var currentUserLocation: LatLng? = null
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        goToCurrentLocation()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    private fun checkAndGetPermission() {
        if (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        checkAndGetPermission()
        map = googleMap

        map?.let { currentMap ->
            currentMap.apply {
                isMyLocationEnabled = true
                currentUserLocation?.let {
                    currentMap.apply {
                        uiSettings.isZoomControlsEnabled = true
                        animateCamera(CameraUpdateFactory.zoomTo(ZOOM), 5000, null)
                    }
                }
            }
        }
    }


    private fun createLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = TIME_INTERVAL
            fastestInterval = FASTEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @SuppressLint("MissingPermission")
    private fun goToCurrentLocation() {

        var currentActivity = this.requireActivity()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(createLocationRequest()!!)
        val client: SettingsClient = LocationServices.getSettingsClient(currentActivity)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener { _ ->

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentActivity)

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let { userCoordinates ->
                        currentUserLocation = LatLng(userCoordinates.latitude, userCoordinates.longitude)
                        map?.apply {
                            uiSettings.isZoomControlsEnabled = true
                            currentUserLocation?.let { addMarker(MarkerOptions().position(it)) }
                            animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLocation, ZOOM))
                        }
                    }
                }
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(this.activity, REQUEST_CODE)
                } catch (sendEx: IntentSender.SendIntentException) {
                }
            }
        }


    }
}


