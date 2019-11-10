var UploadDepots = function () {
    $.getJSON('api/depot/all', function (depots) {
        $.each(depots, function(i, v) {
            $('#location')
                .append($("<option></option>")
                    .attr("value", v.location.point.lat + ',' + v.location.point.lng)
                    .text(v.location.country + '(' + v.type + ')'));

            $('#destination')
                .append($("<option></option>")
                    .attr("value", v.location.point.lat + ',' + v.location.point.lng)
                    .text(v.location.country + '(' + v.type + ')'));

            // TODO: when opening for update, select actual values
            // var loc = cargo.location.point.lat + ',' + cargo.location.point.lng;
            // $("#location select").val(loc);

            // var dest = cargo.destination.point.lat + ',' + cargo.destination.point.lng;
            // $("#destination select").val(dest);

        });
    });
};