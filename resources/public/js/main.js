
function ViewModel() {

    var self = this;

    this.selectedLAN = ko.observable(null);

    this.lansToCome = ko.observable(null);

    this.message = ko.observable(null);

    this.newGame = ko.observable(null);

    this.votersMap = ko.observable(null);

    this.upvoteMessage = ko.observable(null);

    this.updateSelectedLAN = function(id){
        $.get('/lans/' + id, null, self.selectedLAN);        
    };

    this.updateVotersMap = function(id){
        $.get('/votemap/' + id, null, self.votersMap);
    };

    Sammy(function() {
        this.get('/', function() {
                     self.selectedLAN(null);
                     $.get('/lans', null, self.lansToCome);
        });
        this.get('#/:id', function() {
                     self.lansToCome(null);
                     self.updateSelectedLAN(this.params.id);
                     self.updateVotersMap(this.params.id);
                 });
    }).run();

    this.proposeGame = function(element) {
        var game = $('#newgame').val();
        var url = '/addgame/' + this.selectedLAN()._id;
        $.post(url, {game: game}, function(){
                   self.updateSelectedLAN(self.selectedLAN()._id);
                   self.newGame(null);
                   window.location.reload();
               });
    };

    this.voters = function(gameName) {
        var map = self.votersMap();
        if (map && map.map){
            return map.map[gameName];
        }
        return null;
    };

    this.upvoteClicked = function(elm) {
        var game = elm[0];
        $.post('/upvote/'+self.selectedLAN()._id, {game: game}, function(data){
                   self.updateVotersMap(self.selectedLAN()._id);
                   if (data)
                       self.upvoteMessage(data);
                   else
                       alert("Jeder nur ein Kreuz!");

               });
    };    
}

ko.applyBindings(new ViewModel());

