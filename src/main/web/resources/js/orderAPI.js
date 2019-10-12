var prefix = '/api/order';

var RestGet = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#data").append(   "<tr><td>" + result[i].id           +   "</td>" +
                                            "<td>" + result[i].done  +   "</td>" +
                                            "<td>" + result[i].cargoDTO.id  +   "</td>" +
                                            "<td>" + result[i].cargoDTO.location  +   "</td>" +
                                            "<td>" + result[i].destination       +   "</td>" +
                                            "<td>" + result[i].truckDTO.plate       +   "</td>" +
                                            "<td>" + result[i].workingDrivers       +   "</td>" +
                                            "<td>Update | Delete</td></tr>");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}