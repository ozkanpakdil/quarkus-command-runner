package main

import (
	"fmt"
	// "log"
	"net"
	"os/exec"
	"time"

	"github.com/gin-contrib/cache"
	"github.com/gin-contrib/cache/persistence"
	"github.com/gin-gonic/gin"
)

type Address struct {
	Ip string `form:"ip"`
}

func main() {
	store := persistence.NewInMemoryStore(24 * time.Hour)
	route := gin.Default()
	route.GET("/whois", cache.CachePage(store, 24*time.Hour, startPage))
	route.OPTIONS("/whois", func(c *gin.Context) { c.String(200, "pong") })
	route.Run(":8083")
}

func startPage(c *gin.Context) {
	var a Address
	if c.ShouldBindQuery(&a) == nil {
		testInput := net.ParseIP(a.Ip)

		if testInput.To4() == nil || testInput.To16() == nil {
			fmt.Printf("%v is not a valid IP address\n", testInput)
			return
		}

		out, _ := exec.Command("/bin/sh", "-c", "torify whois "+a.Ip).CombinedOutput()

		c.String(200, string(out))
	}
}
