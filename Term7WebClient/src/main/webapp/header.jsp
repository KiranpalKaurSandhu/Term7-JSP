<%--Author: Jade Bayot--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta charset="UTF-8">
        <title>Travel Experts</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="style.css" rel="stylesheet">
    <script src="jquery.js"></script>
    <%Object agent = session.getAttribute("agentId");%>
    <script>

        // store logged-in agent for filtering
        var loggedInAgent = "<%=agent%>";
        // populate select fields onload
        $(document).ready(function ()
        {
            //load booking related to logged-in agent
            loadBooking();


            // set items for customer ID select
            $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/customer/getcustomerbyagent/" + loggedInAgent,function (data)
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
            $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getbookingbyagent/" + loggedInAgent ,function(data) {
                data.forEach(function (booking) {
                    $("#deleteBookingId").append(new Option(booking.bookingId ,booking.bookingId));
                    $("#updateBookingId").append(new Option(booking.bookingId ,booking.bookingId));
                });
            });

            // onChange function to populate fields with selected booking in delete
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

            // onChange function to populate insert field with selected booking in update
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


        function loadBooking() {
            $.get("http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getbookingbyagent/"+ loggedInAgent,
                function (data) {
                    var table = $("#bookingTable");
                    table.empty(); // Clear the table before populating it

                    data.forEach(function (booking, index) {
                        var row = $("<tr>");

                        // Apply different background colors for odd and even rows
                        if (index % 2 === 0) {
                            row.addClass('even-row');
                        } else {
                            row.addClass('odd-row');
                        }

                        // Add booking information to each table cell
                        if (booking.packageId != null) {
                            row.append($("<td>").text(booking.bookingId));
                            row.append($("<td>").text(booking.bookingDate));
                            row.append($("<td>").text(booking.bookingNo));
                            row.append($("<td>").text(booking.travelerCount));
                            row.append($("<td>").text(booking.customerId));
                            row.append($("<td>").text(booking.tripTypeId));
                            row.append($("<td>").text(booking.packageId));
                        } else {
                            row.append($("<td>").text(booking.bookingId));
                            row.append($("<td>").text(booking.bookingDate));
                            row.append($("<td>").text(booking.bookingNo));
                            row.append($("<td>").text(booking.travelerCount));
                            row.append($("<td>").text(booking.customerId));
                            row.append($("<td>").text(booking.tripTypeId));
                            row.append($("<td>").text("N/A"));
                        }

                        table.append(row);
                    });
                });
        }


            // get all values in insert form field and prepare as JSON for insert
        function buildInsertJSON()
        {
            if(document.getElementById("bookingDate").value != null && document.getElementById("bookingNo").value != null &&
                document.getElementById("travelerCount").value != null)
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
            }
            else
            {
                alert("All fields are required");
            }

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
                url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/putbooking",
                method: "PUT",
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
                alert("create didnt go through");
            });
            //loadBooking();
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
                //var result = JSON.parse(xhr.responseText);
                //$("#message").html(result.message);
                alert("Booking Deleted Successfully");
            }).fail(function (xhr, text, error){
                //$("#message").html(text + " | " + error);
                //alert(text + " | " + error);
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
                //var result = JSON.parse(xhr.responseText);
                //$("#message").html(result.message);
                alert("Booking Updated Successfully");
            }).fail(function (xhr, text, error){
                //$("#message").html(text + " | " + error);
                alert(text + " | " + error);
            });
        }

    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container" id="navBar">
        <% String agentName = (String) session.getAttribute("agent_name"); %>
        Welcome <%= agentName %>!
        <a class="navbar-brand" href="logout.jsp">Logout</a>
    </div>
</nav>
<div class="container">
