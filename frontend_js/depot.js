const axios = require('axios');
var L = require('leaflet');

var markerOptions = {
    radius: 8,
    fillColor: "#ff7800",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export default function loadAllDepots(leaflet_map) {
    axios.get('api/depot/all')
        .then(depots => {
            console.log(depots.data);

            depots.data.forEach(depot => {
                var loc = depot.location;

                var latLng = L.latLng(
                    parseFloat(loc.point["lat"]), parseFloat(loc.point["lng"])
                );

                L.circleMarker(latLng, markerOptions)
                    .bindPopup(loc.country + ' ' + depot.type)
                    .addTo(leaflet_map);
            });

        })
        .catch(err => {
            console.log(err);
        });
}
