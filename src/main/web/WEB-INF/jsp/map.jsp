<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Leaflet OSRM Example</title>
    <link href="<c:url value="/resources/css/leaflet/leaflet.css" />" rel="stylesheet">

</head>
<body>
    <div id="map" class="map" style="height: 180px"></div>
    <script src="<c:url value="/resources/js/webpack/map.bundle.js" />"></script>
</body>
</html>
