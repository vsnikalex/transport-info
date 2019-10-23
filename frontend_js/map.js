var L = require('leaflet');
require('graphhopper-js-api-client');

import loadAllDepots from './depot.js';

var map = L.map('map').setView([49.095, 16.523], 5);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

function setupRoutingAPI(leaflet_map) {

    var defaultKey = "9dcf0a7e-ee94-4b91-8966-ca7b35411a00";
    var profile = "car";

    var routingLayer = L.geoJson().addTo(leaflet_map);

    var host;
    var ghRouting = new GraphHopper.Routing({key: defaultKey, host: host, vehicle: profile, elevation: false});

    ghRouting.addPoint(new GHInput(48.7525249, 18.1450552));
    ghRouting.addPoint(new GHInput(44.0639782, 40.4920936));

    ghRouting.doRequest()
        .then(function(route){
            // Add your own result handling here
            console.log(route);

            var estDist = (route.paths[0].distance/1000).toFixed(1);
            var estTime = (route.paths[0].time/(1000*60*60)).toFixed(1);

            document.getElementById("est_dist").innerHTML = estDist + 'km';
            document.getElementById("est_time").innerHTML = estTime + 'h';

            var path = route.paths[0];
            routingLayer.addData({
                "type" : "Feature",
                "geometry" : path.points
            });

            if (path.bbox) {
                var minLon = path.bbox[0];
                var minLat = path.bbox[1];
                var maxLon = path.bbox[2];
                var maxLat = path.bbox[3];
                var tmpB = new L.LatLngBounds(new L.LatLng(minLat, minLon), new L.LatLng(maxLat, maxLon));
                map.fitBounds(tmpB);
            }
        })
        .catch(function(err){
            console.error(err.message);
        });
};

setupRoutingAPI(map);

loadAllDepots(map);