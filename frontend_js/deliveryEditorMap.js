const L = require('leaflet');
require('graphhopper-js-api-client');

const map = L.map('map').setView([48.43, 18.56], 4);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

const depotLayerGroup = L.layerGroup().addTo(map);
const cargoLayerGroup = L.layerGroup().addTo(map);

const routingLayer = L.geoJSON().addTo(map);
let ghOptimization;

window.onload = function() {
    let apiKey = "9dcf0a7e-ee94-4b91-8966-ca7b35411a00";
    let profile = "truck";
    let host;

    ghOptimization = new GraphHopperOptimization({
        key: apiKey,
        host: host,
        vehicle: profile,
        elevation: false,
        optimize: true
    });
};

function fillInfoTable(estDist, estTime) {
    document.getElementById("est_dist").innerHTML = estDist + 'km';
    document.getElementById("est_time").innerHTML = estTime + 'h';
}

export function clearRoutes() {
    routingLayer.clearLayers();
}

export function optimizeRoute() {
    clearRoutes();
    ghOptimization.clear();

    let startDepot = depotLayerGroup.getLayers()[0];
    let startCoords = startDepot.getLatLng();
    ghOptimization.addPoint(new GHInput(startCoords.lat, startCoords.lng));

    let routePoints = cargoLayerGroup.getLayers();
    if (routePoints.length === 0) {
        return new Promise(function (resolve, reject) {
            fillInfoTable(0, 0);
            resolve(0.0);
        })
    }
    routePoints.forEach(destination => {
        let destCoords = destination.getLatLng();
        ghOptimization.addPoint(new GHInput(destCoords.lat, destCoords.lng));
    });

    return ghOptimization.doTSPRequest()
        .then(function (response) {
                let routes = response.solution.routes[0];
                routes.points.forEach(line =>
                    routingLayer.addData({
                        "type" : "Feature",
                        "geometry" : line
                    })
                );

                let estDist = (response.solution.distance/1000).toFixed(1);
                let estTime = (response.solution.time/60/60).toFixed(1);

                fillInfoTable(estDist, estTime);

                return estTime;
            }
        ).catch(function(err){
            console.error(err.message);
        });
}

const depotMarkerStyle = {
    radius: 8,
    fillColor: "#ff7800",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export function markDepot(depot) {
    if (typeof depot === 'undefined') { return; }

    let loc = depot.location;

    let latLng = L.latLng(
        parseFloat(loc.point["lat"]), parseFloat(loc.point["lng"])
    );

    L.circleMarker(latLng, depotMarkerStyle)
        .bindPopup(loc.country + ' ' + depot.type)
        .addTo(depotLayerGroup);
}

export function clearDepots() {
    depotLayerGroup.clearLayers();
}

const cargoDestinationMarkerStyle = {
    radius: 6,
    fillColor: "#1367ed",
    color: "#000",
    weight: 1,
    opacity: 1,
    fillOpacity: 0.8
};

export function markCargoDestination(cargo) {
    if (cargo === null) { return; }

    let endLoc = cargo.endDepot.location;

    let latLng = L.latLng(
        parseFloat(endLoc.point["lat"]), parseFloat(endLoc.point["lng"])
    );

    let cargoMarker = L.circleMarker(latLng, cargoDestinationMarkerStyle)
        .bindPopup(endLoc.country + ' ' + cargo.endDepot.type);

    cargoMarker._id = cargo.id;

    cargoMarker.addTo(cargoLayerGroup);
}

export function clearCargoes() {
    cargoLayerGroup.clearLayers();
}

export function removeCargoMarker(id) {
    let cargoMarker = cargoLayerGroup.getLayers().find(layer =>
        layer._id == id
    );

    cargoLayerGroup.removeLayer(cargoMarker)
}