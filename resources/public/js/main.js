
function ViewModel() {

    var self = this;

    this.selectedLAN = ko.observable(null);

    this.lansToCome = ko.observable(null);

    this.message = ko.observable(null);

    this.newGame = ko.observable(null);

    this.votersMap = ko.observable(null);

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
               });
    };

    this.voters = function(gameName) {
        var map = self.votersMap();
        if (map && map.map){
            return map.map[gameName];
        }
        return null;
    };

}

ko.applyBindings(new ViewModel());

