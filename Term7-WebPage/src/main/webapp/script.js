
    // populate select fields onload
    $(document).ready(function ()
    {
        loadBooking();

        // set items for customer ID select
        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/customer/getallcustomers", function (data)
    {
        data.forEach(function (customer)
    {
        $("#customerId").append(new Option(customer.custFirstName + " " +customer.custLastName, customer.CustomerId));
    });
    });


        // set items for triptype ID select
        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/triptype/getalltriptypes", function (data) {
        data.forEach(function (triptype) {
        $("#tripTypeId").append(new Option(triptype.tTName, triptype.tripTypeId));
    });
    });

        // set items for package ID select
        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/package/getallpackages", function (data) {
        data.forEach(function (package)
    {
        $("#packageId").append(new Option(package.pkgName, package.packageId));
    });
    });


        // set items for booking ID in update and delete select
        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getallbookings",function (data) {
        data.forEach(function (booking) {
        $("#deleteBookingId").append(new Option(booking.bookingId ,booking.bookingId));
        $("#updateBookingId").append(new Option(booking.bookingId ,booking.bookingId));
    });
    });

        // onChange function to populate insert field with selected booking in delete
        $("#deleteBookingId").change(function ()
    {
        var bookingId = $("#deleteBookingId").val().toString();

        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getbooking/" + bookingId,
        function (data){
        $("#bookingDate").val(data.bookingDate);
        $("#bookingNo").val(data.bookingNo);
        $("#travelerCount").val(data.travelerCount);
        $("#customerId").val(data.customerId);
        $("#tripTypeId").val(data.tripTypeId);
        $("#packageId").val(data.packageId);
    });
    });


        $("#updateBookingId").change(function ()
    {
        var bookingId = $("#updateBookingId").val().toString();

        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getbooking/" + bookingId,
        function (data){
        $("#bookingDate").val(data.bookingDate);
        $("#bookingNo").val(data.bookingNo);
        $("#travelerCount").val(data.travelerCount);
        $("#customerId").val(data.customerId);
        $("#tripTypeId").val(data.tripTypeId);
        $("#packageId").val(data.packageId);
    });
    });
    });


    // populate booking list
    function loadBooking()
    {
        $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getallbookings",
            function (data){
                var ul = $("#bookingList");
                data.forEach(function (booking){
                    if(booking.packageId != null)
                        var li = $("<li class='list-group-item d-flex justify-content-between align-items-center'>").text("Booking: " + booking.bookingId + " " + booking.bookingDate + " " + booking.bookingNo + " " + booking.travelerCount + " " + booking.customerId + " " + booking.tripTypeId + " " + booking.packageId);
                    else
                        var li = $("<li class='list-group-item d-flex justify-content-between align-items-center'>").text("Booking: " + booking.bookingId + " " + booking.bookingDate + " " + booking.bookingNo + " " + booking.travelerCount + " " + booking.customerId + " " + booking.tripTypeId);

                    ul.append(li);
                });
            });
    }

    // get all values in insert form field and prepare as JSON for insert
    function buildInsertJSON()
    {
        var data = "{" +
        "'bookingId': '0', " +
        "'bookingDate': '" + document.getElementById("bookingDate").value + "', " +
        "'bookingNo': '" + document.getElementById("bookingNo").value + "', " +
        "'travelerCount': '" + document.getElementById("travelerCount").value + "', " +
        "'customerId': '" + document.getElementById("customerId").value + "', " +
        "'tripTypeId': '" + document.getElementById("tripTypeId").value + "', " +
        "'packageId': '" + document.getElementById("packageId").value + "'" +
        "}";
        return data;
    }

    // get all values in insert form field and prepare as JSON for post/update
    function buildUpdateJSON()
    {
        var data = "{" +
        "'bookingId': '" + document.getElementById("updateBookingId").value + "', " +
        "'bookingDate': '" + document.getElementById("bookingDate").value + "', " +
        "'bookingNo': '" + document.getElementById("bookingNo").value + "', " +
        "'travelerCount': '" + document.getElementById("travelerCount").value + "', " +
        "'customerId': '" + document.getElementById("customerId").value + "', " +
        "'tripTypeId': '" + document.getElementById("tripTypeId").value + "', " +
        "'packageId': '" + document.getElementById("packageId").value + "'" +
        "}";
        return data;
    }

    // ajax function to insert to database
    function putBooking()
    {
        var jsonString = buildInsertJSON();
        $.ajax({
        url: "http://localhost:8080/Term7-WebPage-1.0-SNAPSHOT/api/booking/putbooking",
        method: "PUT",
        config: { headers:{
        "Access-Control-Allow-Headers": "Origin, Content-Type, Accept, Authorization",
        "Access-Control-Allow-Origin": "*"
    }
    },
        crossDomain:true,
        data: jsonString,
        accept: "application/json",
        dataType: "json",
        contentType: "application/json"
    }).done(function (data, text, xhr){
        var result = JSON.parse(xhr.responseText);
        //$("#message").html(result.message);
        alert(result);
    }).fail(function (xhr, text, error){
        //$("#message").html(text + " | " + error);
        alert(text + " | " + error);
    });
        loadBooking();
    }

    // ajax function for delete
    function deleteBooking()
    {
        var selectedBookingId = document.getElementById("deleteBookingId").value;

        $.ajax({
        url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/deletebooking/" + selectedBookingId,
        method: "DELETE",
        config: { headers:{
        "Access-Control-Allow-Headers": "Origin, Content-Type, Accept, Authorization",
        "Access-Control-Allow-Origin": "*"
    }
    },
        "crossDomain":true,
        accept: "application/json",
        dataType: "json",
        contentType: "application/json"
    }).done(function (data, text, xhr){
        var result = JSON.parse(xhr.responseText);
        //$("#message").html(result.message);
        alert(result);
    }).fail(function (xhr, text, error){
        //$("#message").html(text + " | " + error);
        alert(text + " | " + error);
    });
    }

    //ajax function for update
    function postBooking()
    {
        var jsonString = buildUpdateJSON();
        $.ajax({
        url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/postbooking",
        method: "POST",
        data: jsonString,
        accept: "application/json",
        dataType: "json",
        contentType: "application/json"
    }).done(function (data, text, xhr){
        var result = JSON.parse(xhr.responseText);
        //$("#message").html(result.message);
        alert(result);
    }).fail(function (xhr, text, error){
        //$("#message").html(text + " | " + error);
        alert(text + " | " + error);
    });
    }
