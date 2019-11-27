var prefix = 'api/truck';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            if (response === true) {
                window.location = '/transport/admin/truck';
            } else {
                alert('TRYING TO DELETE TRUCK ASSIGNED TO DELIVERY');
            }
        },
        error: function(e) {
            console.log(e.responseText);
            alert('REQUEST PROCESSING ERROR');
        }
    });
};

var ToUpdatePage = function (id) {
    location.href='/transport/admin/truck_add?id=' + id;
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
                        "<td>" + result[i].location.country       + ": " + result[i].location.city       +  "</td>" +
                        "<td>" + result[i].driversCnt     +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(" + result[i].id + ")'>" +
                                "&#9998;</button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(" + result[i].id + ")'>" +
                                "&#10006;</button>" +
                            "</div>" +
                        "</td>" +
                    "</tr>"
                );
            }
        },
        error: function(e) {
            console.log(e.responseText);
            alert('REQUEST PROCESSING ERROR');
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
            window.location = '/transport/admin/truck_add';
        },
        error: function(e) {
            console.log(e.responseText);
            alert('REQUEST PROCESSING ERROR');
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
            window.location = '/transport/admin/truck';
        },
        error: function(e) {
            console.log(e.responseText);
            alert('REQUEST PROCESSING ERROR');
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
