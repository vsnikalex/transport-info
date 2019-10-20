var prefix = '/api/truck';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            window.location = '/admin_truck';
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
};

var ToUpdatePage = function (id) {
    location.href='/admin_truck_add?id=' + id;
};

var RestGetAll = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {

                $("#data").append(
                    "<tr>" +
                        "<td>" + result[i].plate           +   "</td>" +
                        "<td>" + result[i].capacity  +   "</td>" +
                        "<td>" + result[i].status       +   "</td>" +
                        "<td>" + result[i].location.country       +   "</td>" +
                        "<td>" + result[i].driversCnt     +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(" + result[i].id + ")'>" +
                                "<i class=\"icon icon-edit\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(" + result[i].id + ")'>" +
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

var RestPut = function(id) {
    var JSONObject= {
        'id' : id,
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

var SetUp = function() {

    UploadDepots();

    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('id')) {
        $.getJSON(prefix + '/' + urlParams.get('id'), function (truck) {
            $("#entity_info").text(
                truck.plate + ": " +
                truck.capacity + "kg " +
                truck.status + " " +
                truck.location.country
            );
            $("#plate").val(truck.plate);
            $("#capacity").val(truck.capacity);

            $("#save_button").on('click', function () {
                RestPut(truck.id);
            });
        });
    } else {
        $("#save_button").on('click', function () {
            RestPost();
        });
    }
};