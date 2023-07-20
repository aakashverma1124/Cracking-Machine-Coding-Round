### Description:
- Build an app that lets patients connect to doctors and book appointments. 
- The day is divided into time slots of 30 minutes each. 
- Doctors can login to the portal and declare their availability for the given day in terms of slots. Assume that login/logout functionalities are already implemented.
- Patients can book appointments/ cancel existing appointments. 
- For simplicity you can assume that the doctors’ availability is declared for that particular day only.

### Features:
- A new doctor should be able to register, and mention his/her speciality among (Cardiologist, Dermatologist, Orthopedic, General Physician, Dentist, etc).
- A doctor should be able to declare his/her availability in each slot for the day. For example, the slots will be of 30 minutes like 9:00 to 9.30, 13:00 to 13:30.
- Patients should be able to register. 
- Patients should be able to search available slots based on speciality.
- The slots should be displayed in a ranked fashion. Default ranking strategy should be to rank by start time. But we should be able to plugin more strategies like Doctor’s rating etc in the future.
- Patients should be able to book appointments with a doctor for an available slot. 
- A patient can book multiple appointments in a day. 
- A patient cannot book two appointments with two different doctors in the same time slot.
- Patients can also cancel an appointment, in which case that slot becomes available for someone else to book.
- Build a wait-list feature:
- If the patient wishes to book a slot for a particular doctor that is already booked, then add this patient to the wait-list.
- If the patient with whom the appointment was booked originally cancels the appointment, then the first in the wait-list gets the appointment.
- A patient/doctor should be able to view his/her booked appointments for the day.

### Bonus requirements:
#### Trending Doctor: 

- Maintain at any point of time which doctor has the most appointments. 
- Different ranking strategy can be used like rating of doctor as a strategy to display a list of available doctors for a given specialization.