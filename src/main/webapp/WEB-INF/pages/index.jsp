<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Test</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
           <h3><img height="50" width="55" src="<c:url value="/static/logo.pnd"/>"/><a href="/">Worker List</a></h3>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul id="positionList" class="nav navbar-nav">
                            <li><button type="button" id="add_worker" class="btn btn-default navbar-btn">Add Worker</button></li>
                            <li><button type="button" id="add_position" class="btn btn-default navbar-btn">Add Position</button></li>
                            <li><button type="button" id="delete_worker" class="btn btn-default navbar-btn">Delete Worker</button></li>
                            <li><button type="button" id="reset_table" class="btn btn-default navbar-btn">Reset table</button></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Positions <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/">Default</a></li>
                                    <c:forEach items="${positions}" var="group">
                                        <li><a href="/position/${position.id}">${position.name}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" name="pattern" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

            <table class="table table-striped">
                <thead>
                <tr>
                    <td></td>
                    <td><b>Name</b></td>
                    <td><b>Surname</b></td>
                    <td><b>E-mail</b></td>
                    <td><b>Group</b></td>

                </tr>
                </thead>
                <c:forEach items="${workers}" var="workers">
                    <tr>
                        <td><input type="checkbox" name="toDelete[]" value="${workers.id}" id="checkbox_${workers.id}"/></td>
                        <td>${workers.name}</td>
                        <td>${workers.surname}</td>
                        <td>${workers.phone}</td>
                        <td>${workers.email}</td>
                        <c:choose>
                            <c:when test="${workers.position ne null}">
                                <td>${workers.group.name}</td>
                            </c:when>
                            <c:otherwise>
                                <td>Default</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${allPages ne null}">
                        <c:forEach var="i" begin="1" end="${allPages}">
                            <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${byPositionsPages ne null}">
                        <c:forEach var="i" begin="1" end="${byPositionsPages}">
                            <li><a href="/position/${positionId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                </ul>
            </nav>
        </div>

        <script>
            $('.dropdown-toggle').dropdown();

            $('#add_contact').click(function(){
                window.location.href='/worker_add_page';
            });

            $('#add_group').click(function(){
                window.location.href='/position_add_page';
            });

            $('#delete_contact').click(function(){
                var data = { 'toDelete[]' : []};
                $(":checked").each(function() {
                    data['toDelete[]'].push($(this).val());
                });
                $.post("/worker/delete", data, function(data, status) {
                    window.location.reload();
                });
            });
        </script>
    </body>
</html>