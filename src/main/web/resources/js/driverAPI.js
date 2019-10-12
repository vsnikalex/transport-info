var prefix = '/api/driver';

var RestGet = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#data").append(   "<tr><td>" + result[i].id           +   "</td>" +
                                            "<td>" + result[i].firstName  +   "</td>" +
                                            "<td>" + result[i].lastName       +   "</td>" +
                                            "<td>" + result[i].workedThisMonth       +   "</td>" +
                                            "<td>" + result[i].action     +   "</td>" +
                                            "<td>" + result[i].location     +   "</td>" +
                                            "<td>" + result[i].truckDTO.plate     +   "</td>" +
                                            "<td>Update | Delete</td></tr>");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}