var prefix = '/api/truck';

var RestDelete = function(plate) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + plate,
        async: true,
        success: function(response) {
            window.location = '/admin_truck';
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
};

var ToUpdatePage = function (plate) {
    location.href='/admin_truck_add?plate=' + plate;
};

var RestGetAll = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {

                var plate = result[i].plate;

                $("#data").append(
                    "<tr>" +
                        "<td>" + plate           +   "</td>" +
                        "<td>" + result[i].capacity  +   "</td>" +
                        "<td>" + result[i].status       +   "</td>" +
                        "<td>" + result[i].location.country       +   "</td>" +
                        "<td>" + result[i].driversCnt     +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(\"" + plate + "\")'>" +
                                "<i class=\"icon icon-edit\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(\"" + plate + "\")'>" +
                                "<i class=\"icon icon-cancel\" aria-hidden=\"true\"></i></button>" +
                            "</div>" +
                        "</td>" +
                    "</tr>"
                );
            }
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
};

var RestPost = function() {
    var JSONObject= {
        'plate': $("#plate").val(),
        'capacity': $("#capacity").val(),
        'status': $("#status").val(),
        'coords': $("#location").val()
    };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url:  prefix + '/add',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        cache: false,
        async: true,
        success: function(result) {
            alert(result.msg);
            window.location = '/admin_truck_add';
        },
        error: function(e) {
            // TODO: highlight error fields with red
            alert(e.responseText);
        }
    });
};

var RestPut = function() {
    var JSONObject= {
        'plate': $("#plate").val(),
        'capacity': $("#capacity").val(),
        'status': $("#status").val(),
        'coords': $("#location").val()
    };

    $.ajax({
        type: 'PUT',
        url:  prefix + '/update',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        cache: false,
        async: true,
        success: function(result) {
            alert(result.msg);
            window.location = '/admin_truck';
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
};

var IfForUpdate = function() {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('plate')) {
        $.getJSON(prefix + '/' + urlParams.get('plate'), function (truck) {
            $("#entity_info").text(
                truck.plate + ": " +
                truck.capacity + "kg " +
                truck.status + " " +
                truck.location.country
            );
            $("#plate").val(truck.plate);
            $("#capacity").val(truck.capacity);

            $("#save_button").on('click', function () {
                RestPut();
            });
        });
    } else {
        $("#save_button").on('click', function () {
            RestPost();
        });
    }
};