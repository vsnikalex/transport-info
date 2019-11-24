var UploadDepots = function () {
    $.getJSON('api/depot/all', function (depots) {
        $.each(depots, function(i, v) {
            $('#location')
                .append($("<option></option>")
                    .attr("value", v.id)
                    .text(v.location.country + '(' + v.type + ')'));

            $('#destination')
                .append($("<option></option>")
                    .attr("value", v.id)
                    .text(v.location.country + '(' + v.type + ')'));

        });
    });
};
