# theaterBooking
A theater booking project buit on SpringBoot, mysql, Hibernate, Lombok.

Core Features:

• This system focuses only on a use case where multiple users trying to book tickets to the same movie show. However, make sure the data model supports multiple movies, shows, and cinema halls. 
• A user can choose up to 6 seats from a cinema hall
• A user has to pay for the seats within 2 minutes. If not, the seats will be released for other users to book. 
• Assume payment is handled by a third-party payment system. The outcome of the payment API will be either success or a failure. 
• Seats are blocked on a first-come-first-served basis. Unfortunately, if the same seat is chosen by more than one user, then pick one user who blocks the max number of seats. If there is a tie between users, then pick one user randomly and reject everyone else. 
• Scalable system - multiple instances of the application should be able to work together to book tickets 
• Assume a single instance of a database is used for maintaining the state   

API:

• Provide APIs to access this system's service 
• Secure the APIs Please commit the code to Github and invite superops-ai as a collaborator to your repository.
