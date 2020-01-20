<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="requestPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<header>
    <link rel="stylesheet" href="${requestPath}/assets/css/internal/style.css">
    <link rel="stylesheet" href="${requestPath}/assets/css/external/bootstrap.min.css">
    <link rel="stylesheet" href="${requestPath}/assets/css/external/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${requestPath}/assets/css/external/jquery-ui.css">

    <script type="text/javascript" src="${requestPath}/assets/js/external/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/external/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/external/jquery-ui.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/external/bootstrap.min.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/external/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/external/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/internal/main.js"></script>
    <script type="text/javascript" src="${requestPath}/assets/js/internal/action.js"></script>
</header>
<body>
<h2>${message} Spring MVC 2 </h2>
<div>
<table id="coursePersonsTableId" class="table table-striped table-bordered" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Pin</th>
        <th>Start Date</th>
        <th>Salary</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Pin</th>
        <th>Start Date</th>
        <th>Salary</th>
        <th>Delete</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach items="${persons}" var="ps">
        <tr>
            <td>${ps.id}</td>
            <td>${ps.name}</td>
            <td>${ps.surname}</td>
            <td>${ps.pin}</td>
            <td>${ps.startDate}</td>
            <td>${ps.salary}</td>
            <td><a href="remove?id=${ps.id}" style="color: red">Delete</a></td>
            <%--<td>item:  ${item}, Snider</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
<%--Modal includes:--%>

<button id="showPersonDialog">New Person</button>
<br>


    <!-- Modal -->
    <div id="addPersonModalId" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Person Add</h4>
                </div>
                <div class="modal-body">
                    <form method="post" action="addPerson">
                        <label for="personNameId">Name: </label><input name="name" id="personNameId" type="text" class="form-control">
                        <label for="personSurnameId">Surname: </label><input name="surname" id="personSurnameId" type="text" class="form-control">
                        <label for="personPinId">Pin: </label><input name="pin" id="personPinId" type="text" class="form-control">
                        <label for="personSalaryId">Salary: </label><input name="salary" id="personSalaryId" type="text" class="form-control">
                        <label for="personCreateDateId">Start Date: </label><input name="createDate" id="personCreateDateId" type="text" class="form-control datepicker">

                        <input type="submit" id="addSubmitBtnId" class="btn-primary" value="Add">
                        <span id="showInfo"></span>
                    </form>


                </div>
                <div class="modal-footer">
                    <%--<button type="button" id="addPersonBtnId" class="btn btn-primary" >Add</button>--%>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>






<%--<form action="addStudent" method="post">--%>

    <%--<label for="studentName">Student name: </label><input type="text" name="name" value="${student.name}" id="studentName"><br>--%>
    <%--<label for="studentSurname">Student surname: </label><input type="text" name="surname" value="${student.surname}" id="studentSurname"><br>--%>
    <%--<label for="studentPin">Student pin: </label><input type="text" name="pin" value="${student.pin}" id="studentPin"><br>--%>
    <%--<label for="studentPhone">Student phone: </label><input type="text" name="phone" value="${student.phone}" id="studentPhone"><br>--%>

    <%--<input type="submit" value="save">--%>

<%--</form>--%>

</body>
</html>
