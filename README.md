# forecast
Simple command line forecast Java app

Description
----

Historical weather forecast analysis app. It currently supports two modes, `daily` and `hourly`.
* **daily** - shows temperature statistics from the last 7 days
* **hourly** - shows temperature and precipitation data for the last 48 hours


Installation
----

* Install [Java JDK](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html) and add it to PATH in environmental variables
* Download .zip file from [here](https://github.com/Mroxny/forecast/releases)
* Open CMD and type `java -jar forecast.jar <arg 1> <arg 2> etc...`
* Enjoy!

How to use
----
The application must be supplied with 4 arguments.
* OpenWeather API Key - 32-character key generated on the [OpenWeather](https://home.openweathermap.org/users/sign_up) website
* Path to a configuration file, with each line containing comma-separated values: city name, latitude, longitude
* Forecast type (*daily*, *hourly*)
* Name of the city (if more than two words, city names must be in quotation marks, <br/> for example: `"Buenos Aires"`)

Example run:
`java -jar forecast.jar [API key] config.txt hourly Warsaw`
