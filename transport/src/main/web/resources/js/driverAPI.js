var prefix = 'api/driver';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            if (response === true) {
                window.location = '/transport/admin/driver';
            } else {
                alert('TRYING TO DELETE SINGLE DRIVER ON DELIVERY');
            }
        },
        error: function(e) {
            console.log(e.responseText);
        }
    });
};

var ToUpdatePage = function (id) {
    location.href='/transport/admin/driver_add?id=' + id;
};

var RestGetAll = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {

                var id = result[i].id;

                $("#data").append(
                    "<tr>" +
                        "<td>" + id           +   "</td>" +
                        "<td>" + result[i].firstName  +   "</td>" +
                        "<td>" + result[i].lastName       +   "</td>" +
                        "<td>" + result[i].workedThisMonth       +   "</td>" +
                        "<td>" + result[i].action     +   "</td>" +
                        "<td>" + result[i].location.country     +   "</td>" +
                        ((result[i].truckDTO === null) ? "<td>not assigned</td>" : "<td>" + result[i].truckDTO.plate + "</td>") +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(" + id + ")'>" +
                                "&#9998;</button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(" + id + ")'>" +
                                "&#10006;</button>" +
                            "</div>" +
                        "</td>" +
                    "</tr>"
                );
            }
        },
        error: function(e) {
             console.log(e.responseText);
        }
    });
};

var RestPost = function() {
    var JSONObject= {
        'username': $("#username").val(),
        'password': $("#password").val(),
        'firstName': $("#first_name").val(),
        'lastName': $("#last_name").val(),
        'coords': $("#location").val(),
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
            window.location = '/transport/admin/driver_add';
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
        'username': $("#username").val(),
        'firstName': $("#first_name").val(),
        'lastName': $("#last_name").val(),
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
            window.location = '/transport/admin/driver';
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
        $.getJSON(prefix + '/' + urlParams.get('id'), function (driver) {
            $("#entity_info").text(
                driver.firstName + " " + driver.lastName + ": " +
                driver.location.country
            );
            $("#first_name").val(driver.firstName);
            $("#last_name").val(driver.lastName);

            $("#username").val(driver.username);
            $("#username-field").hide();
            $("#password-field").hide();

            $("#save_button").on('click', function () {
                RestPut(driver.id);
            });
        });
    } else {
        $("#save_button").on('click', function () {
            RestPost();
        });
    }
};
