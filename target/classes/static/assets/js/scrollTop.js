$(function () {
    $('[href*=#]').click(function () {
        if (this.pathname == undefined && this.hostname == undefined && this.hash == undefined) {
            var $target = $(this.getAttribute('href'));
            if ($target.length) {
                var targetOffset = $target.offset().top - 100;
                $('html,body').animate({
                    scrollTop: targetOffset
                },
                    300);
            }
            return false;
        }
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var $target = $(this.hash);
            $target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');
            if ($target.length) {
                var targetOffset = $target.offset().top - 100;
                $('html,body').animate({
                    scrollTop: targetOffset
                },
                    300);
                return false;
            }
        }
    });
});

$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    if (scrollTop >= 300) {
        $('.toolbar-return-top').addClass('top-btn-show');
    }
    else {
        $('.toolbar-return-top').removeClass('top-btn-show');
    }
});

