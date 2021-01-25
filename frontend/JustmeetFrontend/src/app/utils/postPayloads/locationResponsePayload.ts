import LatLng = google.maps.LatLng;
import GeocoderLocationType = google.maps.GeocoderLocationType;
import LatLngBounds = google.maps.LatLngBounds;

export interface locationResponsePayload {
  results: [{
    types: [string],
    formatted_address: string,
    address_components: [{
      short_name: string,
      long_name: string,
      postcode_localities: [string],
      types: [string]
    }],
    partial_match: boolean,
    place_id: string,
    postcode_localities: [string],
    geometry: {
      location: LatLng,
      location_type: GeocoderLocationType
      viewport: LatLngBounds,
      bounds: LatLngBounds
    }
  }],
}
