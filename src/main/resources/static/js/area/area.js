var tempId = null;
var areaInitUrl = null;
getOption = function (id, name) {
    return "<option value='" + id + "'>" + name + "</option>";
};

clearOption = function($select) {
    $select.html("");
    $select.append("<option value=\"0\">请选择</option>");
};

addOption = function ($select, json){
    console.log(json);
    for (var i in json) {
        var option = getOption(json[i].id, json[i].name);
        $select.append(option);
    }
};

initNextArea = function(value, next) {
    if (typeof(next) !== 'undefined' && next !== ''){
        tempId = value;
        $.get(areaInitUrl+"?id="+ value, function (data) {
            if (data !== null && value === tempId) {
                var $select = $('#' + next);
                clearOption($select);
                addOption($select, data.subArea);
            }
        });
    }
};

/**
 *
 * @param url
 * @param value
 * @param note 联动显示的顺序
 */
startArea = function(url, value, note) {
    areaInitUrl = url;
    initNextArea(value, note)
};