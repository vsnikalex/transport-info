var prefix = '/api/truck';

var RestGet = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#data").append(   "<tr><td>" + result[i].plate           +   "</td>" +
                                            "<td>" + result[i].capacity  +   "</td>" +
                                            "<td>" + result[i].status       +   "</td>" +
                                            "<td>" + result[i].location       +   "</td>" +
                                            "<td>" + result[i].driversCnt     +   "</td>" +
                                            "<td>Update | Delete</td></tr>");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}