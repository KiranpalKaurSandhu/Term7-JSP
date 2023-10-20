$.ajax({
    url: 'Bookings/api/booking/getallbookings',
    method: 'GET',
    success: function (data) {
        let bookingList = $('#booking-list');
        data.forEach(function (booking){

            // Log the booking object to the console !!! for testing
            console.log(booking);

            // link to booking-details
            const bookingLink = `<a href="booking-details.html?id=${booking.bookingId}">${booking.bookingNo}</a>`;

            let row = '<tr>' +
                '<td>' + booking.bookingId + '</td>' +
                '<td>' + booking.bookingDate + '</td>' +
                '<td>' + booking.bookingNo + '</td>' +
                '<td>' + booking.travelerCount + '</td>' +
                '<td>' + booking.customerId + '</td>' +
                '<td>' + booking.tripTypeId + '</td>' +
                '<td>' + booking.packageId + '</td>' +
                '<tr>';
            bookingList.append(row);
        })
    },
    error: function (error){
        //handle errors
    }
});
