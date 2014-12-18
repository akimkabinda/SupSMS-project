/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
  $('[data-toggle="popover"]').popover({
      delay: { "show": 200, "hide": 100 },
      trigger: 'hover',
      html: true
  });
});


