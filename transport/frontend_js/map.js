var L = require('leaflet');
require('graphhopper-js-api-client');
const axios = require('axios');

var map = L.map('map').setView([49.095, 16.523], 4);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

const corpMarkerOptions = {
    radius: 8,
    fillColor: "#ff7800",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

const clientMarkerOptions = {
    radius: 6,
    fillColor: "#001eff",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

function loadAllDepotsToMap(leaflet_map) {
    axios.get('api/depot/all')
        .then(depots => {
            depots.data.forEach(depot => {
                var loc = depot.location;

                var latLng = L.latLng(
                    parseFloat(loc.point["lat"]), parseFloat(loc.point["lng"])
                );

                let markerOptions;
                switch (depot.type) {
                    case 'CORP':
                        markerOptions = corpMarkerOptions;
                        break;
                    case 'CLIENT':
                        markerOptions = clientMarkerOptions;
                        break;
                }

                L.circleMarker(latLng, markerOptions)
                    .bindPopup(loc.country + ' ' + depot.type)
                    .addTo(leaflet_map);
            });

        })
        .catch(err => {
            console.log(err);
        });
}

loadAllDepotsToMap(map);
