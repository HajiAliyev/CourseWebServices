<html>
<body>
<h2>${message}</h2>

<form action="addStudent" method="post">
    <label for="studentName">Student name: </label><input type="text" name="name" value="${student.name}" id="studentName"><br>
    <label for="studentSurname">Student surname: </label><input type="text" name="surname" value="${student.surname}" id="studentSurname"><br>
    <label for="studentPin">Student pin: </label><input type="text" name="pin" value="${student.pin}" id="studentPin"><br>
    <label for="studentPhone">Student phone: </label><input type="text" name="phone" value="${student.phone}" id="studentPhone"><br>

    <input type="submit" value="save">
</form>

</body>
</html>
