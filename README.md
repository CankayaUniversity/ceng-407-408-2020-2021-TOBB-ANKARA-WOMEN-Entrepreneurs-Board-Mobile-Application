# ceng-407-408-2020-2021-TOBB-ANKARA-WOMEN-Entrepreneurs-Board-Mobile-Application
TOBB ANKARA WOMEN Entrepreneurs Board Mobile Application

# Getting Started

## Tech
* [Ionic Framework](https://ionicframework.com/docs/intro/cli) - A framework for building native apps using Ionic Angular.
* [node.js](https://nodejs.org/en/download/) - for the frontend
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - for the backend

## Project Structure
* Use this if you want to run the application using IDE

File > Project Structure > Modules > + > Import Module > Select backend folder > Import module from external model > Select Maven > Finish 
File > Project Structure > Modules > + > Import Module > Select frontend folder > Create module from existing sources > Next > Next > Finish

File > Project Structure > Project > Select Project SDK as Java 11 

File > Project Structure > SDKs > Select 11

## Build Setup 
  ```sh
$ npm install -g @ionic/cli #to install ionic

```

Use this if you cannot start the application using IDE
 ```sh
$ ionic serve #reload at localhost:4200 
              
```

If necessary, do not forget to update these dependencies below
 ```sh
$ npm install ionic2-calendar --save 
$ npm i --save date-fns
$ npm install @ngx-translate/core --save 
$ npm i ng2-search-filter --save             
$ npm install ngx-autosize
 
```



