
function ViewModel() {

    var self = this;

    this.selectedLAN = ko.observable(null);

    this.lansToCome = ko.observable(null);

    Sammy(function() {
        this.get('/', function() {
                     self.selectedLAN(null);
                     $.get('/lans', null, self.lansToCome);
        });
        this.get('#/:id', function() {
                     self.lansToCome(null);
                     $.get('/lans/' + this.params.id, null, self.selectedLAN);
                 });
    }).run();
}

ko.applyBindings(new ViewModel());

