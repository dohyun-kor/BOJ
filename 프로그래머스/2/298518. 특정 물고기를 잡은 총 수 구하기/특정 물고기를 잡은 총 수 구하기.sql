select count(*) as FISH_COUNT from FISH_INFO as fi
join FISH_NAME_INFO as fni using(FISH_TYPE)
where fni.FISH_NAME in ('BASS','SNAPPER');