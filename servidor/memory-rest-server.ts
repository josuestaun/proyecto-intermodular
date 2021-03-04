const mongoose = require('mongoose');
const express = require('express');
const bodyParser = require('body-parser');

mongoose.connect('mongodb+srv://projectmemorygame:4Vientos@memorydb.lwrzy.mongodb.net/puntuacionesdb?retryWrites=true&w=majority', {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

const app = express();

let puntuacionesSchema = new mongoose.Schema({
    nombre: {
        type: String,
        required: true,
        minlength: 1,
    },
    puntos: {
        type: Number,
        minlength: 1,
    },
});

let Puntuacion = mongoose.model('puntuaciones', puntuacionesSchema);

app.use(function (req: any, res: any, next: any) {
    res.header("Access-Control-Allow-Origin", "*"); // update to match the domain you will make the request from
    res.header("Access-Control-Allow-Methods", 'GET,PUT,POST,DELETE,OPTIONS');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.use(
    bodyParser.urlencoded({
      extended: true
    })
)
   
app.use(bodyParser.json())

app.get('/puntuaciones', async (req:any, res:any) => {
    const puntuaciones = await Puntuacion.find({});
    console.log(puntuaciones);
    
    try {
      res.send(puntuaciones);
    } catch (err) {
      res.status(500).send(err);
    }
});

app.post('/puntuaciones', async (req:any, res:any) => {
    const puntuacion = new Puntuacion({
      nombre: req.body.nombre,
      puntos: req.body.puntos,
    });
   
    try {
      await puntuacion.save();
      res.send(puntuacion);
    } catch (err) {
      res.status(500).send(err);
    }
});

app.delete('/puntuaciones/:id', async (req:any, res:any) => {
    try {
      const puntuacion = await Puntuacion.findByIdAndDelete(req.params.id)
   
      if (!puntuacion) res.status(404).send("No item found")
      res.status(200).send()
    } catch (err) {
      res.status(500).send(err)
    }
});

app.put('/puntuacion/:id', async (req:any, res:any)=> {
    try {
      const puntuacion = await Puntuacion.findByIdAndUpdate(req.params.id, req.body)
      await Puntuacion.save()
      res.send(puntuacion)
    } catch (err) {
      res.status(500).send(err)
    }
});

// This is not working with Heroku, IP and PORT are automatically asigned
//const server = app.listen(8000, "localhost", () => {
// const { address, port } = server.address();

// console.log('Listening on %s %s', address, port);
// });


// start the server listening for requests
app.listen(process.env.PORT || 3000, 
    () => console.log("Server is running..."));