Test plan for method GT4500::fire(FiringMode)  
Three out of five test are used to cover the more complicated single firing mode.

mode | setup | test |excepted results
--- | --- | --- | ---
single | both stores are full | fire twice | first then secondary fires once| 
single |  first store is empty | fire twice | secondary fires twice
single |  second store is empty | fire twice | fist fires twice
all | both stores are full | fire once | both sotes are used once
all | first sote is empty | fire once | secondary store fires, primary doesn't