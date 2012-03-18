
function ViewModel() {

    var self = this;

    this.selectedLAN = ko.observable(null);

    this.lansToCome = ko.observable(null);

    this.message = ko.observable(null);

    this.newGame = ko.observable(null);

    this.updateSelectedLAN = function(id){
        $.get('/lans/' + id, null, self.selectedLAN);        
    };

    Sammy(function() {
        this.get('/', function() {
                     self.selectedLAN(null);
                     $.get('/lans', null, self.lansToCome);
        });
        this.get('#/:id', function() {
                     self.lansToCome(null);
                     self.updateSelectedLAN(this.params.id);
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
}

ko.applyBindings(new ViewModel());

