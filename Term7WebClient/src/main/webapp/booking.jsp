<jsp:include page="header.jsp" />
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
              <!--<input type="text" class="form-control" name="customerId" id="customerId">-->
              <select name="customerId" id="customerId" class="form-control"></select>
            </div>
            <div class="form-group">
              <label for="bookingNo">Trip Type:</label>
              <!--<input type="text" class="form-control" name="tripTypeId" id="tripTypeId">-->
              <select name="tripTypeId" id="tripTypeId" class="form-control"></select>
            </div>
            <div class="form-group">
              <label for="bookingNo">Package Id:</label>
              <!--<input type="text" class="form-control" name="packageId" id="packageId">-->
              <select name="packageId" id="packageId" class="form-control"></select>
            </div>
            <button type="submit" class="btn btn-primary" id="btnNewBooking">Create Booking</button>
          </form></div>
        </div>
        </div>


          <div class="col-md-6">
            <div class="card mb-4">
              <div class="card-header bg-success text-white">Update Booking</div>
              <div class="card-body">
                <form id="updateBookingForm">
                  <div class="form-group">
                    <label for="updateBookingId">Booking ID:</label>
                    <!--<input type="text" class="form-control" name="bookingId" id="updateBookingId">-->
                    <select id="updateBookingId" class="form-control"><option>Select Booking Id</option></select>
                  </div>
                  <%--            <div class="form-group">--%>
                  <%--              <label for="updateBookingDate">New Booking Date:</label>--%>
                  <%--              <input type="text" class="form-control" name="bookingDate" id="updateBookingDate">--%>
                  <%--            </div>--%>
                  <!-- Add more input fields for updating other booking properties -->
                  <button type="submit" class="btn btn-success" id="btnUpdateBooking">Update Booking</button>
                </form>
              </div>
            </div>
          </div>



        <div class="card">
          <div class="card-header bg-danger text-white">Delete Booking</div>
            <div class="card-body">
              <form id="deleteBookingForm">
                <div class="form-group">
                  <label for="deleteBookingId">Booking ID:</label>
                <!--<input type="text" class="form-control" name="bookingId" id="deleteBookingId">-->
                  <select id="deleteBookingId" class="form-control"><option>Select Booking Id</option></select>
                </div>
                <button id="btnDeleteBooking" type="submit" class="btn btn-danger">Delete Booking</button>
              </form>
            </div>
          </div>



    <div class="card mt-4">
      <div class="card-header bg-info text-white">List of Bookings</div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Booking ID</th>
          <th>Booking Date</th>
          <th>Booking No</th>
          <th>Traveler Count</th>
          <th>Customer ID</th>
          <th>Trip Type ID</th>
          <th>Package ID</th>
        </tr>
        </thead>
        <tbody id="bookingTable">
        <!-- JavaScript to dynamically populate the table with bookings -->
        </tbody>
      </table>
    </div>


      <script>
        // onclick listeners for buttons
        $("#btnNewBooking").click(putBooking);
        $("#btnDeleteBooking").click(deleteBooking);
        $("#btnUpdateBooking").click(postBooking);
      </script>
  </div>
</div>
</body>
</html>
<%--<jsp:include page="footer.jsp" />--%>



