var Data = {
    tempId: null,
    reaInitUrl: null,
    areaList: [], // []
    nodeList: {} // {}
};
var Fun = {
    getOption: function (id, name) {
        return "<option value='" + id + "'>" + name + "</option>";
    },
    clearOption: function($select) {
        $select.html("");
        $select.append("<option value=\"0\">请选择</option>");
    },
    addOption : function ($select, json){
        for (var i in json) {
            var option = Fun.getOption(json[i].id, json[i].name);
            $select.append(option);
        }
    },
    initNextArea: function(value, next) {
        if (typeof(next) !== 'undefined' && next !== ''){
            tempId = value;
            if (Data.areaList.hasOwnProperty(value)) {
                var data = Data.areaList[value];
                if (data !== null && value === tempId) {
                    var $select = $('#' + next);
                    Fun.addOption($select, data.subArea);
                }
            } else {
                $.get(Data.areaInitUrl+"?id="+ value, function (data) {
                    if (data !== null && value === tempId) {
                        var $select = $('#' + next);
                        Fun.addOption($select, data.subArea);
                        Data.areaList[value] = data;
                    }
                });
            }
        }
    }
};


/**
 *
 * @param url
 * @param value
 * @param note 联动显示的顺序
 */
startArea = function(url, value, noteList) {
    // areaInitUrl = url;
    // initNextArea(value, note)
    if (typeof(url) === 'undefined' || url==null) {
        return;
    }
    if (typeof(value) === 'undefined') {
        return;
    }
    if (typeof(noteList) === 'undefined' || noteList == null || noteList.length == 0) {
        return;
    }

    Data.areaInitUrl = url;
    Data.nodeList = noteList;
    var len = Data.nodeList.length;
    Fun.initNextArea(0,noteList[0]);
    for (var i = 0; i < len - 1; i++) {
        $('#'+noteList[i]).change(function () {
            var index = Data.nodeList.indexOf(this.id);
            for (var j = index + 1; j < Data.nodeList.length; j++) {
                Fun.clearOption($("#"+ Data.nodeList[j]));
            }
            Fun.initNextArea(this.value, Data.nodeList[index+1])
        });
    }
};