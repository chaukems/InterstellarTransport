<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interstellar Transport</title>

        <link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet"/>

        <!-- jQuery -->
        <script src="<c:url value='/resources/js/jquery-3.3.1.slim.min.js'/>"></script>
        <script src="<c:url value='/resources/js/bootstrap.bundle.min.js'/>"></script>

        <!-- Angular -->
        <script src="<c:url value='/resources/js/angular.js'/>"></script>
        <!-- Angular -->
        <script src="<c:url value='/resources/js/app.js'/>"></script>

    </head>
    <body>

        <div ng-app = "interstellarApp" ng-controller = "interstellarController as $ctrl">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">            <!-- Navbar brand -->
                <a class="navbar-brand" href="#">Interstellar Transport</a>
            </nav>

            <!-- Page Content -->
            <div class="container">
                <!-- Default form login -->
                <form class="text-center border border-light p-5">

                    <p class="h4 mb-4">Interstellar Transport</p>
                    <!-- source -->
                    <input  ng-model="$ctrl.searchForm.source" type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Source">
                    <!-- Destination -->
                    <input  ng-model="$ctrl.searchForm.destination" type="text" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Destination">

                    <!-- Sign in button -->
                    <button type="button" ng-click="$ctrl.getShortestPath($ctrl.searchForm)" class="btn btn-info btn-block my-4" type="submit">Sign in</button>
                </form>
                <div>Entry {{$ctrl.searchForm| json}}</div>
                <br><br>

                <table class="table table-striped table-bordered jambo_table">
                    <thead>
                        <tr class="headings">
                           
                            <th class="column-title">Distance to destination</th>
                            <th class="column-title">Path</th>																							
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class=" ">{{$ctrl.results.distance}}</td>
                            <td class=" ">{{$ctrl.results.path}}</td>
                        </tr>																						
                    </tbody>
                </table> 

            </div>
            <!-- /.container -->

        </div>
    </body>
</html>


