var prefix = '/api/cargo';

var RestGet = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {
                $("#cargoes").append(   "<tr><td>" + result[i].id           +   "</td>" +
                                            "<td>" + result[i].description  +   "</td>" +
                                            "<td>" + result[i].weight       +   "</td>" +
                                            "<td>" + result[i].status       +   "</td>" +
                                            "<td>" + result[i].location     +   "</td>" +
                                            "<td>Update | Delete</td></tr>");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}