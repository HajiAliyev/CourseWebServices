$(function () {
    $('#showPersonDialog').click(function () {
        alert('Add person Button is working');
        // $('#addPersonModalId').load(('include/addPersonDialog.html',function () {
        $('#addPersonModalId').modal('show');
        // });
    });
    $('#addPersonBtnId').click(function () {
        var name = $('#personNameId').val();
        var surname = $('#personSurnameId').val();
        var pin = $('#personPinId').val();
        var startDate = $('#personCreateDateId').val();
        var salary = $('#personSalaryId').val();

        console.log(name);
        console.log(surname);
        console.log(pin);
        console.log(startDate);
        console.log(salary);


        $.ajax({
            url: 'addPerson',
            method: 'POST',
            data: 'name='+name+'&surname='+surname+'&pin='+pin+'&startDate='+startDate+'&salary='+salary,
            success: function () {
                $('#showInfo').html('Info added');
                $('#addPersonModalId').modal('hide');
                location.reload();
            }
        })
    });
});