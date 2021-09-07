<h1>Auth API</h1>

<h3>CinemaHall API</h3>

>Add cinema cinemaHall
```
POST
/cinemaHall ? name = <cinemaHallName> & seatingCapacity = <Integer>
```

>Update seating seatingCapacity of cinemaHall
```
PUT
/cinemaHall/seatingCapacity ? Id = <cinemaHallId> & seatingCapacity = <Integer>
```

>update CinemaHall Name
```
PUT
/cinemaHall/name ? Id = <cinemaHallId> & name = <cinemaHallName>
```

<h3>Movie API</h3>

>addNewMovie
```
POST
/movie ?  name = <movieName> & durationInMinutes = <Integer>
```

##MovieShowController API

>add New Movie Show
```
POST
/movieShow ? movieId = <movieId> & hallId = <cinemaHallId> & startTime = <TimeInMilliSeconds>
```

>Payment Status Controller
```
POST
/paymentStatus ? transactionID = <transactionID> & paymentStatus = <"SUCCESS"/"FAIL"/"INPROGRESS">
```
<h3>Seat Booking Controller API</h3>

>openSeatsForBooking
```
POST
/seatBooking/open ? showID = <Id>
```

<h1>UnAuth API</h1>

<h3>Cinema Hall Viewer</h3> 

>getAllCinemaHall - get all cinema hall details
```
GET
/cinemaHallViewer
```

<h3>MovieShowViewer</h3>

>getAllMovieShows - get all movies shows list
```
GET
/movieShow
```

<h3>MovieViewer</h3>

>getAllMovie - get all movies list
```
GET
/movieViewer
```

<h3>SeatBookingHandler</h3>

>getSeatStatusForShowById - get seats free / occupied status
```
GET
/seatBookingHandler/seatStatus ? showID = <Id>
requestBooking - request seats for booking
```

>POST
```
/seatBookingHandler/request
{
    movieShowID : <ID>,
    seat : [seatsNOs]
}
Returns - booking ID
```

>bookingStatus - check booking status
```
GET
/seatBookingHandler/bookingStatus ? bookingId = <ID>
returns "SUCCESS"/"FAIL"/"INPROGRESS"
```
