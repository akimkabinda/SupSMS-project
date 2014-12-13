var gulp = require('gulp'),//GULP !!!
    less = require('gulp-less'),//Convert less to css
    concat = require('gulp-concat'),//Concatenation of files
    clean = require('gulp-clean'),//Remove folder
    rename = require('gulp-rename');//Rename file
    
var styleLocation = './*.less';
var styleDestination = './../SupSMS-project-war/web/core/css'; 

//Style
gulp.task('style', function(){
    return gulp.src(styleLocation)
        .pipe(concat('all.css'))
        .pipe(less())
        .pipe(gulp.dest(styleDestination));
});

gulp.task('default', ['style']);

//Clean
/*gulp.task('clean',function(){
    return gulp.src('./../web/core', {read: false})
        .pipe(clean({force: true}));
});*/

