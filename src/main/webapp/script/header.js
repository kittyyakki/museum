jQuery.noConflict();
jQuery(document).ready(function($) {
    
    var run_delayed = {
        playing: false,
        
        play: function() {
            $('#logo_outline01').stop().animate({'fill-opacity':0},500);
            $('#logo_outline02').stop().animate({'fill-opacity':0},500);
            $('#logo_outline03').stop().animate({'fill-opacity':0},500);
            $('#logo_outline04').stop().animate({'fill-opacity':0},500);
            $('#logo_outline05').stop().animate({'fill-opacity':0},500);
            $('#logo_outline06').stop().animate({'fill-opacity':0},500);
            $('#logo_outline07').stop().animate({'fill-opacity':0},500);
            
            new Vivus("header_logo_v", {
                type: "delayed",
                start: "autostart",
                delay: 250,
                duration: 300,
            }, function(){
                $('#logo_outline01').stop().animate({'fill-opacity':1},500);
                $('#logo_outline02').stop().animate({'fill-opacity':1},500);
                $('#logo_outline03').stop().animate({'fill-opacity':1},500);
                $('#logo_outline04').stop().animate({'fill-opacity':1},500);
                $('#logo_outline05').stop().animate({'fill-opacity':1},500);
                $('#logo_outline06').stop().animate({'fill-opacity':1},500);
                $('#logo_outline07').stop().animate({'fill-opacity':1},500);
            });
            
            this.playing = true;
        },
        
        stop: function() {
            // Additional logic if needed
            this.playing = false;
        }
    };
    
    run_delayed.play();

    $('#header_logo_v').click(function(e){
        e.preventDefault();
        if(!run_delayed.playing){
            run_delayed.play();
        }
    });
});
