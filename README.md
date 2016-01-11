# UMSSearch
Search plugin for Universal Media Server

#Usage
Pattern:
Keys       | [Search...]
-----------|------------
`[abc]`    | `[def]`
`[ghi]`	   | `[jkl]`
`[mno]`    | `[pqr]`
`[stuv]`   | `[wxyz]`
`[. _-]`   | `[0-9]`

to search "my movie.mkv"
[ **m** no] > [wx **y** z] > [.  _-] > [ **m** no] > [mn **o** ] > [stu **v** ] > [Search...]
**my mov** ie.mkv
