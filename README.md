# My Calendar App

## Overview
Welcome to MyCalendarApp - My very first full-fledged project as a self-taught, aspiring programmer! Beyond honing me new found skills by working on something practical and functional, this project focused on implementing what I have learned about Java, software design, data management.  

MyCalendar App is a Java-based application designed to help users manage their schedules by adding, removing, and viewing events on a calendar. The application features a GUI implemented using AWT, allowing users to interact with the calendar visually.

## Features

**Add Events:** Allows the user to schedule specific events on a specific date and time.

**Remove Events:** Allows the user to simply delete a scheduled event.

**Month & Year Navigation:** Allows for the ability to select any month and any year from the current up until 2050. The calendar properly displays leap years.

**Event Conflict:** The program will warn the user if they are double booking a time slot by checking if an exsiting event is already scheduled at the same time and date.

**Event Sorting:** Event data is sorted by start time when loaded, ensuring the events of the daya re displayed to the user in chronological order.

**Real-Time Updating:** The calendar interface is immediately updated following the removal or adding of a event.

** Gray Buttons (Inactive Days):** These represent inactive days from the previous or next month, used to ensure that the first day of the current month is correctly aligned with the appropriate day of the week. 

## Technologies Used

**Java:** This was the core programming language used for developing the application.

**AWT (Abstract Window Toolkit):** Used to create the app's GUI.

**Java Collections Framework:** Used for management of the users events. Specifically by utilizing a map structure for efficient starage and retrival.

**JSON:** Utilized for reading and writing event data to files in JSON format so that user events where properly stored and not lost between app uses.

# Usage

**View Calendar:** Displays the current month and events for the current day. Allows navigation of month and year.

![Screenshot 2024-09-10 185714](https://github.com/user-attachments/assets/4c4e0752-3c2f-49d6-997a-39703b3e2a6a)

## Adding Event
Select a day by clicking on it in the GUI, the selected day button will change to yellow. Once a day is selected, click on the "Add Event" button in the bottom right corner.


https://github.com/user-attachments/assets/09b55658-f30c-472b-894e-694bf655aadb

A secondary window will open on screen, enter the following event details:
1) Event Name : Name of the events. This field is mandatory and must be unique to the day of event. If left empty, new event will not be saved and a warning will be displayed.
2) Description : Short description of event.
3) Start Time : Start time of the specific event entered in hh:mm format.
4) Duration: Duration of event entered in minutes.

![New Event detail page](https://github.com/user-attachments/assets/43bcc769-f22f-4708-aca9-94c6d120b97e)

*Adding event with empty name field*
![Empty Event name field warning](https://github.com/user-attachments/assets/a75130fb-8fb5-4398-aafa-8013cede4398)

## Removing Event
Select the day of the scheduled event to be removed by clicking on the day. Once highlighted, click on the "Remove Event" button in the bottom right corner. A secondary window will open, prompting you for the even name only. once entered click "Remove".

https://github.com/user-attachments/assets/5ec99db2-c982-44a9-b4c9-98cb7254e46b

## Month and Year Navigation
Using the drop down menus at the very top of the window, select a desired month and year, from the current year till 2050 to view.

https://github.com/user-attachments/assets/5119920e-7c41-40e2-a1b3-d4489a3c6070


## License:

Released under the [MIT license](https://github.com/Pookie119/calendarApp/blob/main/LICENSE).







