# PaperlessREST

## HOWTO

### Steps to generate REST Server:

1. Start just with openapi-gen.sh and swagger.json
2. Run ```./openapi-gen.sh```
3. The generated soltiuon will be in ```out/``` - open with IntelliJ and try out if source was generated as expected.
4. move all files from the ```out/``` directory to the current working directory, e.g.
   ```mv -rf out/* .```
5. Start IntelliJ again and open the project in the current working directory
6. Run the server with ```mvn spring-boot:run```
7. Open the browser in http://localhost:8080/

### Next steps:

* create fake implementations in ApiApiController to try out the REST-Server

Created by Bhinder Joben, Ghulam Raza and Yamin Siyar.