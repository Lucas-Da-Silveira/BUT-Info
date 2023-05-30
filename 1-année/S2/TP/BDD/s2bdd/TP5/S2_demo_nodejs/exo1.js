var fs = require('fs'); 

fs.readdir('.', function(err, files) {  // fonction en asynchrone
  if(err) {
    console.log('Un probleme est arrive');  // tester avec un mauvais dossier
  }
  else{        // console.log(files);
    for (var i=0;i<files.length;i++){
      console.log(files[i]);
    }
  }
});
console.log('hello avant !!')
