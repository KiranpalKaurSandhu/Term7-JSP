$(document).ready(function() {
    // Initial setup
    disableFormFields(); // Disable form fields by default
    loadBookingDetails(); // Load booking details when page loads

    // Edit button click event
    $('#edit-button').click(function() {
        enableFormFields(); // Enable form fields for editing
    });

    // Save button click event
    $('#save-button').click(function() {
        saveBookingDetails(); // Save  edited details
    });

    // disable form fields
    function disableFormFields() {
        $('#booking-details-form input').prop('disabled', true);
        $('#save-button').prop('disabled', true);
    }

    // edit booking details
    function enableFormFields() {
        // Code to enable form fields
        $('#booking-details-form input').prop('disabled', false);
        $('#save-button').prop('disabled', false);
    }

    // Function to load booking details
    function loadBookingDetails() {
        $.ajax({
            url:'Bookings/api/booking/getbooking', //!!! double check url
            method: 'GET',
            success: function (data) {
                // Populate form fields
                $('#bookingDetailId').val(data.bookingDetailId);
                $('#itineraryNo').val(data.itineraryNo);
                $('#tripStart').val(data.tripStart);
                $('#tripEnd').val(data.tripEnd);
                $('#description').val(data.description);
                $('#destination').val(data.destination);
                $('#basePrice').val(data.basePrice);
                $('#agencyCommission').val(data.agencyCommission);
                $('#bookingId').val(data.bookingId);
                $('#regionId').val(data.regionId);
                $('#classId').val(data.classId);
                $('#feeId').val(data.feeId);
                $('#productSupplierId').val(data.productSupplierId);

            },
            error: function (error) {
                // Handle errors
            }
        });
    }
    //save changes
    function saveBookingDetails()  {
        const updatedData = {
            bookingDetailId: $('#bookingDetailId').val(),
            itineraryNo: $('#itineraryNo').val(),
            tripStart: $('#tripStart').val(),
            tripEnd: $('#tripEnd').val(),
            description: $('#description').val(),
            destination: $('#destination').val(),
            basePrice: $('#basePrice').val(),
            agencyCommission: $('#agencyCommission').val(),
            bookingId: $('#bookingId').val(),
            regionId: $('#regionId').val(),
            classId: $('#classId').val(),
            feeId: $('#feeId').val(),
            productSupplierId: $('#productSupplierId').val()
        };

        $.ajax({
            url: '/Bookings/api/booking/postbooking', //!!! api endpoint url for updating !!!
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updatedData),
            success: function (response) {
                alert('Booking details updated successfully');
            },
            error: function (error) {
                // Handle errors
            }
        });

        disableFormFields();
    }
});
