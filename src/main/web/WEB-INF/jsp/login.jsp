<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Tabellen</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- syntax highlighting CSS -->
    <link href="<c:url value="/resources/css/syntax.css" />" rel="stylesheet">

    <!-- Components CSS -->
    <link href="<c:url value="/resources/css/components.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/theme-dark.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/theme-bevel.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/theme-debug.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>

<body>

    <header class="brand-header" id="DocsHeader">
        <div class="brandbar">
            <div class="container-fixed">
                <div class="brand-logo">
                    <img src="<c:url value="/resources/images/deutsche-telekom-logo.svg" />" alt="Telekom Logo">
                    <span class="sr-only">Telekom Logo</span>
                </div>
                <div class="brand-claim">
                    <img src="<c:url value="/resources/images/brand-claim.svg" />" alt="Brand Claim">
                    <span class="sr-only">Brand Claim</span>
                </div>
            </div>
        </div>
    </header>

    <div class="main" style="min-height: calc(100vh - 167px);">

        <div class="container-fixed">
            <div class="row">
                <div class="col-l-3"></div>
                <div class="col-l-6">
                    <h1 class="underline">Authentication Form</h1>
                    <div class="tc-example">
                        <fieldset class="form-fieldset">
                            <div class="form-input-set">
                                <label for="text7">Login</label>
                                <input type="text" class="form-input" name="text7" id="text7" >
                            </div>
                            <div class="form-input-set">
                                <label for="text8">Password</label>
                                <input type="password" class="form-input" name="text8" id="text8" >
                            </div>
                            <button type="button" class="btn btn-default btn-block">Log In</button>
                        </fieldset>
                    </div>
                </div>
                <div class="col-l-3"></div>
            </div>
        </div>


    </div>

    <jsp:include page="footer.jsp" />

    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/components.min.js" />"></script>
    <script src="<c:url value="/resources/js/docs.js" />"></script>

</body>

</html>
