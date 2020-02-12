SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Ian Wilhelmsen
-- Create date: 2/8/2020
-- Description:	Adds a Menu Item
-- =============================================
CREATE PROCEDURE dbo.AddMenuItemToMenuItems
	-- Add the parameters for the stored procedure here
	@MenuItemType int, @ItenName varChar(50), @Price int, @PriorityScore int, @ExecutionTime int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO [dbo].[MenuItem]
           ([MenuItemType]
           ,[ItemName]
           ,[Price]
           ,[PriorityScore]
           ,[ExecutionTime])
     VALUES
           (@MenuItemType
           ,@ItenName
           ,@Price
           ,@PriorityScore
           ,@ExecutionTime)

	select @@IDENTITY as 'ID'
END
GO
