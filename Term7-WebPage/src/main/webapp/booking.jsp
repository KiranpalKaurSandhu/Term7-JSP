<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">">
  <script src="jquery.js"></script>
</head>
<body>
<div class="container mt-4">
  <h1 class="text-center mb-4">Booking Management</h1>

  <div class="row">
    <div class="col-md-6">
      <div class="card mb-4">
        <div class="card-header bg-primary text-white">Create Booking</div>
        <div class="card-body">
          <form id="createBookingForm">
            <div class="form-group">
              <label for="bookingDate">Booking Date:</label>
              <input type="text" class="form-control" name="bookingDate" id="bookingDate">
            </div>
            <div class="form-group">
              <label for="bookingNo">Booking No:</label>
              <input type="text" class="form-control" name="bookingNo" id="bookingNo">
            </div>
            <div class="form-group">
              <label for="bookingNo">Traveler Count:</label>
              <input type="text" class="form-control" name="travelerCount" id="travelerCount">
            </div>
            <div class="form-group">
              <label for="bookingNo">Customer Id:</label>
              <input type="text" class="form-control" name="customerId" id="customerId">
            </div>
            <div class="form-group">
              <label for="bookingNo">Trip Type:</label>
              <input type="text" class="form-control" name="tripTypeId" id="tripTypeId">
            </div>
            <div class="form-group">
              <label for="bookingNo">Package Id:</label>
              <input type="text" class="form-control" name="packageId" id="packageId">
            </div>
            <button type="submit" class="btn btn-primary">Create Booking</button>
          </form>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <div class="card mb-4">
        <div class="card-header bg-success text-white">Update Booking</div>
        <div class="card-body">
          <form id="updateBookingForm">
            <div class="form-group">
              <label for="updateBookingId">Booking ID:</label>
              <input type="text" class="form-control" name="bookingId" id="updateBookingId">
            </div>
            <div class="form-group">
              <label for="updateBookingDate">New Booking Date:</label>
              <input type="text" class="form-control" name="bookingDate" id="updateBookingDate">
            </div>
            <!-- Add more input fields for updating other booking properties -->
            <button type="submit" class="btn btn-success">Update Booking</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="card">
    <div class="card-header bg-danger text-white">Delete Booking</div>
    <div class="card-body">
      <form id="deleteBookingForm">
        <div class="form-group">
          <label for="deleteBookingId">Booking ID:</label>
          <input type="text" class="form-control" name="bookingId" id="deleteBookingId">
        </div>
        <button type="submit" class="btn btn-danger">Delete Booking</button>
      </form>
    </div>
  </div>

  <div class="card mt-4">
    <div class="card-header bg-info text-white">List of Bookings</div>
    <ul class="list-group list-group-flush" id="bookingList">
      <!-- Sample list item for a booking -->
      <li class="list-group-item d-flex justify-content-between align-items-center">
        Booking ID: 1
        <span class="badge badge-primary badge-pill">Status</span>
      </li>
      <!-- Use JavaScript to dynamically populate the list with bookings -->
    </ul>
  </div>
</div>



<script src="script.js"></script>

<script>
  // Load bookings on page load
  $(document).ready(function () {
    loadBookings();
  });

  // Handle form submission for creating a booking
  $("#createBookingForm").submit(function (event) {
    event.preventDefault();
    createBooking();
  });

  // Handle form submission for updating a booking
  $("#updateBookingForm").submit(function (event) {
    event.preventDefault();
    updateBooking();
  });

  // Handle form submission for deleting a booking
  $("#deleteBookingForm").submit(function (event) {
    event.preventDefault();
    deleteBooking();
  });

  // Function to create a new booking
  function createBooking() {
    // Extract form data
    var bookingData = {
      bookingDate: $("#bookingDate").val(),
      bookingNo: $("#bookingNo").val(),
      travelerCount: $("#travelerCount").val(),
      customerId: $("#customerId").val(),
      tripTypeId: $("#tripTypeId").val(),
      packageId: $("#packageId").val()
    };

    // Send a POST request to create a booking
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/putbooking", // Replace with your service URL
      data: JSON.stringify(bookingData),
      contentType: "application/json",
      success: function (data) {
        alert(data);
        loadBookings(); // Reload the list after creating
      }
    });
  }

  // Function to update an existing booking
  function updateBooking() {
    var bookingId = $("#updateBookingId").val();

    if (bookingId !== "") {
      // Extract form data for the update
      var bookingData = {
        bookingDate: $("#updateBookingDate").val(),
        bookingNo: $("#updateBookingNo").val()
      };

      // Send a PUT request to update the booking
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/putbooking/" + bookingId, // Replace with your service URL
        data: JSON.stringify(bookingData),
        contentType: "application/json",
        success: function (data) {
          alert(data);
          loadBookings(); // Reload the list after updating
        }
      });
    } else {
      alert("Please provide a Booking ID for updating.");
    }
  }

  // Function to delete a booking
  function deleteBooking() {
    var bookingId = $("#deleteBookingId").val();

    if (bookingId !== "") {
      // Send a DELETE request to delete the booking
      $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/deletebooking/" + bookingId, // Replace with your service URL
        success: function (data) {
          alert(data);
          loadBookings(); // Reload the list after deleting
        }
      });
    } else {
      alert("Please provide a Booking ID for deletion.");
    }
  }

  function loadBookings() {
    // Send a GET request to fetch the list of bookings
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Term7-RESTService-1.0-SNAPSHOT/api/booking/getallbookings", // Replace with your service URL
      success: function (data) {
        displayBookings(data);
      }
    });
  }

  // Function to display the list of bookings
  function displayBookings(bookings) {
    // Clear the existing list
    $('#bookingList').empty();

    // Iterate through the retrieved bookings and add them to the list
    $.each(bookings, function (index, booking) {
      addBookingEntry(booking.bookingId, booking.status);

    });
  }

  // Function to add a booking entry to the list
  function addBookingEntry(bookingId, status) {
    // Determine whether to use bg-light or bg-white class based on the list length
    const bgColorClass = $('#bookingList li').length % 2 === 0 ? 'bg-light' : 'bg-white';

    // Create a new list item for the booking with ID and status
    const listItem = `<li class="list-group-item d-flex justify-content-between align-items-center ${bgColorClass}">
        Booking ID: ${bookingId}
        <span class="badge badge-primary badge-pill">${status}</span>
    </li>`;

    // Append the list item to the bookingList
    $('#bookingList').append(listItem);
  }

  // Call the loadBookings function to fetch and display bookings
  loadBookings();

</script>
</body>
</html>