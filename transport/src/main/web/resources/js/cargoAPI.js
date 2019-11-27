var prefix = 'api/cargo';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function() {
            window.location = '/transport/admin/cargo';
        },
        error: function(e) {
            console.log(e.responseText);
            alert('REQUEST PROCESSING ERROR');
        }
    });
};

var ToUpdatePage = function (id) {
    location.href='/transport/admin/cargo_add?id=' + id;
};

var RestGetAll = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for (var i = 0; i < result.length; i++) {

                var id = result[i].id;

                $("#data").append(
                    "<tr>" +
                        "<td>" + id + "</td>" +
                        "<td>" + result[i].description + "</td>" +
                        "<td>" + result[i].weight + "</td>" +
                        "<td>" + result[i].status + "</td>" +
                        "<td>" + result[i].startDepot.location.country + ": " + result[i].startDepot.location.city + "</td>" +
                        "<td>" + result[i].endDepot.location.country + ": " + result[i].endDepot.location.city + "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(" + id + ")'>" +
                                "&#9998;</button>" +
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
        'description': $("#description").val(),
        'startDepotId': $("#location").val(),
        'endDepotId': $("#destination").val(),
        'status' : $("#status").val(),
        'weight' : $("#weight").val()
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
            window.location = '/transport/admin/cargo_add';
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
        'description': $("#description").val(),
        'startDepotId': $("#location").val(),
        'endDepotId': $("#destination").val(),
        'status' : $("#status").val(),
        'weight' : $("#weight").val()
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
            window.location = '/transport/admin/cargo';
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
        $.getJSON(prefix + '/' + urlParams.get('id'), function (cargo) {
            $("#entity_info").text(
                cargo.description + ": " +
                cargo.startDepot.location.country + "\u2192" +
                cargo.endDepot.location.country + " " +
                cargo.status + " " +
                cargo.weight + "kg"
            );
            $("#description").val(cargo.description);
            $("#weight").val(cargo.weight);

            $("#save_button").on('click', function () {
                RestPut(cargo.id);
            });
        });
    } else {
        $("#save_button").on('click', function () {
            RestPost();
        });
    }
};
