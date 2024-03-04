const EventEmitter = require("events").EventEmitter;

var AudioDevice = {
    play: function(track) {
        console.log("Playing : " + track);
    },
    stop: function() {
        console.log("Stopped playing...")
    }
};

// Nous créons une classe événementielle personnalisée.
class MusicPlayer extends EventEmitter {
    constructor() {
        super();
        this.playing = false;
    }
}

var musicPlayer = new MusicPlayer();

musicPlayer.on('play', function(track) {
    this.playing = true;
    AudioDevice.play(track);
});
musicPlayer.on('stop', function() {
    this.playing = false;
    AudioDevice.stop();
});

musicPlayer.emit('play', 'The Roots - The Fire');

setTimeout(function() {
    musicPlayer.emit('stop');
}, 1000);







